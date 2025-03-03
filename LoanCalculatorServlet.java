import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoanCalculator")
public class LoanCalculatorServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        try {
            double loanAmount = Double.parseDouble(request.getParameter("loanAmount"));
            double annualInterestRate = Double.parseDouble(request.getParameter("annualInterestRate"));
            int numberOfYears = Integer.parseInt(request.getParameter("numberOfYears"));
            
            double monthlyInterestRate = annualInterestRate / 1200;
            double monthlyPayment = loanAmount * monthlyInterestRate /
                    (1 - 1 / Math.pow(1 + monthlyInterestRate, numberOfYears * 12));
            double totalPayment = monthlyPayment * numberOfYears * 12;
            
            out.println("<html><body>");
            out.println("<h2>Loan Calculation Result</h2>");
            out.println("<p>Loan Amount: " + loanAmount + "</p>");
            out.println("<p>Annual Interest Rate: " + annualInterestRate + "%</p>");
            out.println("<p>Number of Years: " + numberOfYears + "</p>");
            out.println("<p><strong>Monthly Payment:</strong> " + String.format("%.2f", monthlyPayment) + "</p>");
            out.println("<p><strong>Total Payment:</strong> " + String.format("%.2f", totalPayment) + "</p>");
            out.println("</body></html>");
        } catch (Exception e) {
            out.println("<p>Error in input. Please try again.</p>");
        }
    }
}
