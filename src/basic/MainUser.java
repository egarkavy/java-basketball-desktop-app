package basic;

import Model.Repositories.NewsRepository;
import Model.Tables.News;
import com.intellij.uiDesigner.core.GridConstraints;
import services.WindowService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by super on 5/12/2019.
 */
public class MainUser {
    public JPanel panel;
    private JLabel timer;
    private JTextArea newsElement;
    private JButton button1;
    private JButton teamsBtn;
    private JButton playersBtn;
    private JButton gamesBtn;

    private NewsRepository newsRepository;

    public MainUser() throws SQLException {
        newsRepository = new NewsRepository();

//        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        WindowService.SubscribeOnAppTimer((m, s) -> {
            timer.setText(m + " : " + s);
        });

        FillNews();
//
//        button1.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    //FillNews();
//
//                    newsPanel.add(new Label("hello"), new GridConstraints());
//                    newsPanel.revalidate();
//                    newsPanel.repaint();
//
//                    JLabel l = new JLabel("asdasd", SwingConstants.CENTER);
//                    if (panel != null)
//                        panel.add(l, new GridConstraints());
//
//                    panel.revalidate();
//                    panel.repaint();
//                } catch (Exception e1) {
//                    e1.printStackTrace();
//                }
//            }
//        });
        playersBtn.addActionListener(e -> {
            try {
                WindowService.JustGoToVindow(new Players().panel);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });

        teamsBtn.addActionListener(e -> {
            try {
                WindowService.JustGoToVindow(new Teams().panel);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
    }

    private void FillNews() throws SQLException {
        ArrayList<News> news = (ArrayList<News>) newsRepository.Get();

        GenerateNewsElements(news);
    }

    private void GenerateNewsElements(List<News> newsList) {
        String resultNews = "";
        for(News news : newsList) {
//            JPanel oneNews = GenerateOneNewsElement(news);
            resultNews = resultNews + news.getCreatedAt() + " " + news.getText() + " \n \n";
//            newsPanel.add(oneNews, new GridConstraints());
        }

        newsElement.setText(resultNews);

//        newsPanel.revalidate();
//        newsPanel.repaint();
    }

    private JPanel GenerateOneNewsElement(News news) {
        JPanel wrapper = new JPanel();

        JLabel timestamp = new JLabel();
        timestamp.setText(news.getCreatedAt().toString());

        JLabel newsText = new JLabel();
        newsText.setText(news.getText());

        wrapper.add(timestamp);
        wrapper.add(newsText);

        return wrapper;
    }

}
