package com.mysandbox.helloworld.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ManifestDataModel {
    private String Name;
    private String Author;
    private String Version;
    private String MinimumApiVersion;
    private String UniqueID;
    private String Description;
    private String EntryDLL;
    private List<String> UpdateKeys;
    private String MinimumGameVersion;
    private ContentPackModel ContentPackFor;
}
