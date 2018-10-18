package com.greenox.pos.domain.inventory;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Set;

@Document(collection = "inventory")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Inventory {
    @Id
    public String id;
    public HashMap<String, Set<String>> inventory;
}
