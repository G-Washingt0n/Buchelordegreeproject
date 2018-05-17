package p.martsulg.domain.entry;

import io.reactivex.Observable;
import p.martsulg.data.RestService;
import p.martsulg.domain.UseCase;

/**
 * Created by g_washingt0n on 24.04.2018.
 */

public class ValidTokenUseCase extends UseCase<String, Boolean> {


    @Override
    protected Observable<Boolean> buildUseCase(String param) {

        return RestService.getInstance().checkToken(param);
    }
}
