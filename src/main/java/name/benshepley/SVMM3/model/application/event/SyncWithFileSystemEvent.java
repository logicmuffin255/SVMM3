package name.benshepley.SVMM3.model.application.event;

import lombok.Getter;
import name.benshepley.SVMM3.model.application.ApplicationSyncStateModel;
import org.springframework.context.ApplicationEvent;

@Getter
public class SyncWithFileSystemEvent extends ApplicationEvent {
    private final ApplicationSyncStateModel applicationSyncStateModel;
    public SyncWithFileSystemEvent(Object source, ApplicationSyncStateModel applicationSyncStateModel) {
        super(source);
        this.applicationSyncStateModel = applicationSyncStateModel;
    }
}
