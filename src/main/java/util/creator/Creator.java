package util.creator;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Антон on 07.10.2018.
 */
public interface Creator <T>{
    T create(HttpServletRequest request);
}
