package com.faboda.mailblitz.util;


import com.faboda.curl.ast.ASTNode;
import com.faboda.curl.ast.CurlASTBuilder;
import com.faboda.curl.tokenizer.CurlTokenizer;
import com.faboda.curl.tokenizer.StandardCurlTokenizer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CurlParser {

    public static ASTNode curlToAST(String curlString){
        CurlTokenizer curlTokenizer = new StandardCurlTokenizer(curlString);
        List<String> tokens = curlTokenizer.getTokens();
        return CurlASTBuilder.buildAST(tokens);

    }
}
