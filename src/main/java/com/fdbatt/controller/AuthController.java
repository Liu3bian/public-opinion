package com.fdbatt.controller;

import com.fdbatt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    PasswordEncoder encoder;
//    @Autowired
//    UserMapper userMapper;
//
//    @PostMapping("/login")
//    public Map<String, Object> login(@RequestBody LoginDTO dto) {
//
//        SysUser user = userMapper.selectByUsername(dto.getUsername());
//        if (user == null || !encoder.matches(dto.getPassword(), user.getPassword())) {
//            throw new RuntimeException("登录失败");
//        }
//
//        List<String> perms = userMapper.selectPermissions(user.getId());
//        String token = jwtUtil.generate(user.getId(), perms);
//
//        return Map.of("token", token, "permissions", perms);
//    }

    @PreAuthorize("hasAuthority('sentiment:dashboard:view')")
    @GetMapping("/dashboard")
    public String dashboard() {
        return "OK";
    }
}
