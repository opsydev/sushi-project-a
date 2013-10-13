package filters;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class Throttle
 */
@WebFilter(dispatcherTypes = {
				DispatcherType.REQUEST
				
		}
					, urlPatterns = { "/Throttle", "/Start" }, servletNames = { "Start" })
public class Throttle implements Filter {	

    /**
     * Default constructor. 
     */
    public Throttle() {
        
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		try{
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			
			//Let's get the current IP
			String reqIP = req.getRemoteAddr();
			Long lastTime = req.getSession(false).getLastAccessedTime()/1000;
			Long currentTime = System.currentTimeMillis()/1000;
			if((currentTime-lastTime) < 5){
				resp.sendRedirect("retry.jspx");
				return;
			} 
		
		}catch (Exception e){
			//Not worry about other requests
		}
		chain.doFilter(request, response);
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
