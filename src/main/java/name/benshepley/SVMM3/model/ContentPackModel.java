package com.mysandbox.helloworld.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ContentPackModel {
    private String UniqueID;
    private String MinimumVersion;
}
