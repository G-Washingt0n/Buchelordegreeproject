package p.martsulg.domain.entry;


import io.reactivex.Observable;
import p.martsulg.data.RestService;
import p.martsulg.data.models.LogInUser;
import p.martsulg.data.models.UserInfo;
import p.martsulg.domain.UseCase;

public class LogProfileUseCase extends UseCase<LogInUser, UserInfo> {


    @Override
    protected Observable<UserInfo> buildUseCase(LogInUser param) {

        return RestService.getInstance().logUser(param);


    }
}
