package com.faboda.mailblitz.web.controller;


import com.faboda.curl.ast.ASTNode;
import com.faboda.mailblitz.util.CurlParser;
import com.faboda.mailblitz.web.requests.CurlRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/email")
public class SubscriptionController {
    @PostMapping
    public ASTNode astNode(@RequestBody CurlRequest curlRequest){
        return CurlParser.curlToAST(curlRequest.getCurl());
    }
}
