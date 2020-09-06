package com.ihq.capitalpool.ssoserver.Service;

import com.ihq.capitalpool.common.RestResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "CapitalPoolUserServer")
public interface UserServiceRemote {
    @RequestMapping(value = "/user/detail/upd", method = RequestMethod.GET)
    RestResult selectByUsernamePassword(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password);

    @RequestMapping(value = "/user/detail/uuid", method = RequestMethod.GET)
    RestResult selectByUUID(@RequestParam(value = "uuid") String uuid);
}
