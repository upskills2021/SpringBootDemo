package com.learnspring.application;

import com.learnspring.domain.dto.Name;
import com.learnspring.domain.dto.UserV1;
import com.learnspring.domain.dto.UserV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spring-boot-user")
public class UserVersioningController {
    @GetMapping("v1/user")
    public UserV1 userV1() {
        return new UserV1("Sourav Maitra");
    }

    @GetMapping("v2/user")
    public UserV2 userV2() {
        return new UserV2(new Name("Sourav", "Maitra"));
    }

    @GetMapping(value = "user/param", params = "version=1")
    public UserV1 paramV1() {
        return new UserV1("Sourav Maitra");
    }

    @GetMapping(value = "user/param", params = "version=2")
    public UserV2 paramV2() {
        return new UserV2(new Name("Sourav", "Maitra"));
    }
    @GetMapping(value = "user/header", headers = "X-API-VERSION=2")
    public UserV2 headerV2() {
        return new UserV2(new Name("Bob", "Charle"));
    }

    @GetMapping(value = "user/header", headers = "X-API-VERSION=1")
    public UserV1 headerV1() {
        return new UserV1("Bob Charlie");
    }

    @GetMapping(value = "user/produces", produces = "application/vnd.company.app-v1+json")
    public UserV1 producesV1() {
        return new UserV1("Bob Charlie");
    }
    @GetMapping(value = "user/produces", produces = "application/vnd.company.app-v2+json")
    public UserV2 producesV2() {
        return new UserV2(new Name("Bob", "Charlie"));
    }

}
