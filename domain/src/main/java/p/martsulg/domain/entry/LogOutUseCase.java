package p.martsulg.domain.entry;

import io.reactivex.Observable;
import p.martsulg.data.RestService;
import p.martsulg.domain.UseCase;
import retrofit2.Response;

public class LogOutUseCase extends UseCase<String, Response<Void>> {
    @Override
    protected Observable<Response<Void>> buildUseCase(String token) {
        return RestService.getInstance().logOut(token);
    }
}
