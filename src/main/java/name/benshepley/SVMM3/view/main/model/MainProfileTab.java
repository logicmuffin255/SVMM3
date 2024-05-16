package name.benshepley.SVMM3.view.main.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
@AllArgsConstructor
public class MainProfileTab {
    private @NonNull String title;
}
