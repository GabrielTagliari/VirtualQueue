package virtualqueue.webservice;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/VQ")
public class ApplicationConfig extends Application {

	    public Set<Class<?>> getClasses() {
	        return new HashSet<Class<?>>(Arrays.asList(ProductWebService.class, PedidoWebService.class, UserWebService.class, ItemPedidoWebService.class));
	    }
}

