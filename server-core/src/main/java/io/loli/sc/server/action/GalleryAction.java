package io.loli.sc.server.action;

import io.loli.sc.server.entity.Gallery;
import io.loli.sc.server.entity.User;
import io.loli.sc.server.service.GalleryService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Named
@RequestMapping("gallery")
public class GalleryAction {

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Inject
    private GalleryService gs;

    @RequestMapping(value = "edit/addWithJsonResponse")
    @ResponseBody
    public Gallery addWithJsonResponse(@RequestParam(required = false) String title,
        @RequestParam(required = false) String description, HttpSession session) {
        if (StringUtils.isBlank(title)) {
            title = format.format(new Date());
        }
        if (StringUtils.isBlank(description)) {
            description = format.format(new Date()) + "创建的相册";
        }
        Object userObj = session.getAttribute("user");

        Gallery gallery = new Gallery(); 
        gallery.setUser((User) userObj);
        gallery.setTitle(title);
        gallery.setDescription(description);
        gs.save(gallery);
        return gallery;
    }

    @RequestMapping(value = "edit/add")
    public String add(@RequestParam(required = false) String title, @RequestParam(required = false) String description,
        HttpSession session) {
        if (StringUtils.isBlank(title)) {
            title = format.format(new Date());
        }
        if (StringUtils.isBlank(description)) {
            description = format.format(new Date()) + "创建的相册";
        }
        Object userObj = session.getAttribute("user");

        Gallery gallery = new Gallery();
        gallery.setUser((User) userObj);
        gallery.setTitle(title);
        gallery.setDescription(description);
        gs.save(gallery);
        return "/gallery/show/" + gallery.getId();
    }

    @RequestMapping(value = "img/{id}")
    public String list(@PathVariable(value = "id") int id, Model model, HttpServletRequest request) {
        return "redirect:/gallery/img/" + id + "/" + 1;
    }

    @RequestMapping(value = "fetch/jsonList")
    @ResponseBody
    public List<Gallery> jsonList(HttpSession session) {
        List<Gallery> galList = new ArrayList<>();
        Object user = session.getAttribute("user");
        if (user instanceof User) {
            int uid = ((User) user).getId();
            galList = gs.listByUser(uid);
        }
        return galList;
    }
}
