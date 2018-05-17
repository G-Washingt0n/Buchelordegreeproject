package p.martsulg.domain.trainings;

import io.reactivex.Observable;
import p.martsulg.data.RestService;
import p.martsulg.data.models.UserInfo;
import p.martsulg.domain.UseCase;

/**
 * Created by g_washingt0n on 28.04.2018.
 */

public class GetUserInfoUseCase extends UseCase<String, UserInfo> {

    @Override
    protected Observable<UserInfo> buildUseCase(String string) {
        return RestService.getInstance().getUserInfo(string);
    }
}
