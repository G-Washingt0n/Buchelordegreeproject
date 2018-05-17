package p.martsulg.domain.entry;


import io.reactivex.Observable;
import p.martsulg.data.RestService;
import p.martsulg.data.models.RegisterUser;
import p.martsulg.data.models.UserInfo;
import p.martsulg.domain.UseCase;

public class RegProfileUseCase extends UseCase<RegisterUser, UserInfo> {


    @Override
    protected Observable<UserInfo> buildUseCase(RegisterUser param) {
        return RestService.getInstance().regUser(param);

    }
}
