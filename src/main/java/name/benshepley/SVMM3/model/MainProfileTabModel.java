package name.benshepley.SVMM3.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
@AllArgsConstructor
public class MainProfileTabModel {
    private @NonNull String title;
}
