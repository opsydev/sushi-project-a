package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Mortgage;

/**
 * Servlet implementation class Start
 */
@WebServlet("/Start")
public class Start extends HttpServlet {
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		getServletContext().setAttribute("model", new Mortgage());
	}

	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Start() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String send_to;
		Mortgage m = (Mortgage) getServletContext().getAttribute("model");
		double pay = -1;
		
		//Setup the default amortization
		request.getSession().setAttribute("amortization", getServletContext().getInitParameter("amortization"));
		
		//Let's check if we are getting a fresh request or not
		if(request.getParameter("calculate")==null){
			send_to = "UI.jspx";
		} else {
			//Do computation
			String i = request.getParameter("interest");
			String p = request.getParameter("principle");
			String a = request.getParameter("amortization");
			
			
			//Let's get the data from the stored sessions
			if(request.getParameter("principle")==null){
				p = (String) request.getSession().getAttribute("principle");
			}
			if(request.getParameter("amortization")==null){
				a = (String) request.getSession().getAttribute("amortization");
			}
			
			request.getSession().setAttribute("principle", p);
			request.getSession().setAttribute("amortization", a);
			request.getSession().setAttribute("interest", i);
			
			
			//Let's try computing
			try {
				pay = m.calculatePayment(a, i, p);
				pay = pay*100;
				pay = Math.round(pay);
				pay = pay/100;
				request.setAttribute("pay", pay);
				send_to = "/Result.jspx";
			} catch (Exception e) {
				request.setAttribute("flash_errors", "Unable to calculate: please recheck your values");
				send_to = "/UI.jspx";
			}
		}
		if(request.getParameter("format")!=null){
			if(request.getParameter("format").compareTo("json")==0){
				response.setContentType("text/plain");
				response.getWriter().println(pay);
			}
		}else {
			request.getRequestDispatcher(send_to).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
