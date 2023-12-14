package com.example.ecommercewebapp.library.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> {
    private Page<T> items = Page.empty();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("pageItems: ");
        if(items != null){
            items.forEach(item -> {
                sb.append(item.toString());
                sb.append('\n');
            });
        }
        return sb.toString();
    }
}
