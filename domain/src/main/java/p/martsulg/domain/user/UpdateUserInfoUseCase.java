package p.martsulg.domain.user;

import io.reactivex.Observable;
import p.martsulg.data.RestService;
import p.martsulg.data.models.UserInfo;
import p.martsulg.domain.UseCase;

/**
 * Created by g_washingt0n on 28.04.2018.
 */

public class UpdateUserInfoUseCase extends UseCase<UserInfo, UserInfo> {

    @Override
    protected Observable<UserInfo> buildUseCase(UserInfo info) {
        return RestService.getInstance().updateUser(info);
    }
}
