package listeners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * Application Lifecycle Listener implementation class MaxPrinciple
 *
 */
@WebListener
public class MaxPrinciple implements HttpSessionAttributeListener {

    /**
     * Default constructor. 
     */
    public MaxPrinciple() {
        
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent arg0) {
        
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent arg0) {
        handleAttrubuteChange(arg0);
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent arg0) {
        handleAttrubuteChange(arg0);
    }
    
    public void handleAttrubuteChange(HttpSessionBindingEvent event){    	
    	
    	if(event.getName().compareTo("principle")==0){
    		Double maxPrinciple = (Double) event.getSession().getServletContext().getAttribute("maxPrinciple");
        	if(maxPrinciple==null){
        		maxPrinciple = 0.0;
        		event.getSession().getServletContext().setAttribute("maxPrinciple", maxPrinciple);
        	}
    		String p = (String) event.getSession().getAttribute("principle");
    		
    		try{
    			Double mp = Double.parseDouble(p);
    			
    			maxPrinciple = mp > maxPrinciple ? mp : maxPrinciple;
    		} catch (Exception e){
    			//Do some logging later
    			System.out.println(e.getMessage());
    		}
    		event.getSession().getServletContext().setAttribute("maxPrinciple", maxPrinciple);
    		
    		
    	}
    	
    	
    	
    	
    	
    }
	
}
