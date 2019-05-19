import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.swing.*;

import Model.BasketballDriver;
import Model.Tables.User;
import basic.*;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by super on 4/28/2019.
 */
public class Program {
    public static void main(String args[]) throws SQLException {
        JFrame frame = new JFrame("App");
        frame.setContentPane(new Login().panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
