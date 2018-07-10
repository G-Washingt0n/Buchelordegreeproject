package p.martsulg.domain.entry;

import io.reactivex.Observable;
import p.martsulg.data.RestService;
import p.martsulg.domain.UseCase;

public class LogOutUseCase extends UseCase<String, Void> {
    @Override
    protected Observable<Void> buildUseCase(String token) {
        return RestService.getInstance().logOut(token);
    }
}
