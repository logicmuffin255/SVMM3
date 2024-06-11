package name.benshepley.SVMM3.model.filesystem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileFileSystemModel {
    private String name = "";
    private List<ModFileSystemModel> enabledMods = Collections.emptyList();
    private List<ModFileSystemModel> disabledMods = Collections.emptyList();
}
