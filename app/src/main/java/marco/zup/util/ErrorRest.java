package marco.zup.util;

import android.content.Context;
import android.util.Log;

import org.androidannotations.annotations.EBean;
import org.androidannotations.api.rest.RestErrorHandler;
import org.springframework.core.NestedRuntimeException;

@EBean
public class ErrorRest implements RestErrorHandler {

    private Context context;
    @Override
    public void onRestClientExceptionThrown(NestedRuntimeException e) {
        Log.e("ErrorRest", e.getMessage());
    }

    private void setContext(Context context) {
        this.context = context;
    }
    public static ErrorRest criaDefault(Context context){

        ErrorRest errorRest = new ErrorRest();
        errorRest.setContext( context );
        return errorRest;
    }


}