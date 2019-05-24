package org.tanglizi.oyster.api.controllers.admin;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tanglizi.oyster.api.model.RESTfulResponse;
import org.tanglizi.oyster.common.configurations.OysterCommonConfig;
import org.tanglizi.oyster.common.utils.GlobalCacheKit;
import org.tanglizi.oyster.common.utils.SecurityKit;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/api/v1/admin/login")
public class AdminLoginController {

    @PostMapping
    public ResponseEntity<RESTfulResponse> login(@RequestParam("adminName") String adminName,
                                @RequestParam("adminPassword") String adminPassword,
                                HttpSession session){
        if (null == adminName || null == adminPassword ||
                (!OysterCommonConfig.adminNameList.contains(adminName) || !OysterCommonConfig.adminPasswordList.contains(adminPassword))) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(RESTfulResponse.fail("incorrect admin name or password"));
        }

        String userToken=SecurityKit.getUserToken();
        GlobalCacheKit.getCacheSingleton().set(OysterCommonConfig.USER_TOKEN, userToken);
        session.setAttribute(OysterCommonConfig.USER_TOKEN, userToken);

        return ResponseEntity.ok(RESTfulResponse.ok("login successfully"));
    }

    @DeleteMapping
    public ResponseEntity<RESTfulResponse> logout(HttpSession session){
        GlobalCacheKit.getCacheSingleton().del(OysterCommonConfig.USER_TOKEN);
        session.setAttribute(OysterCommonConfig.USER_TOKEN, null);

        return ResponseEntity.ok(RESTfulResponse.ok("logout successfully"));
    }
}
