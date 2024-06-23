package name.benshepley.SVMM3.model.filesystem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileModel {
    private String name = "";
    private List<ModModel> enabledMods = Collections.emptyList();
    private List<ModModel> disabledMods = Collections.emptyList();
}
