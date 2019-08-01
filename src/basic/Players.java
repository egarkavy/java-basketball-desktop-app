package basic;

import Model.Repositories.CountryRepository;
import Model.Repositories.PlayersRepository;
import Model.Repositories.TeamRepository;
import Model.Tables.Country;
import Model.Tables.Player;
import Model.Tables.Team;
import services.UserService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Created by super on 5/18/2019.
 */
public class Players {
    public JPanel panel;
    private JTable table;
    private JButton addBtn;
    private JButton editBtn;
    private JButton deleteBtn;
    private JTextField nameField;
    private JTextField surnameField;
    private JTextField teamField;
    private JTextField countryField;
    private JTextField numberField;
    private JLabel errorLabel;
    private JPanel editPanel;

    private List<Player> players;
    private PlayersRepository playersRepository;
    private CountryRepository countryRepository;
    private TeamRepository teamRepository;

    private DefaultTableModel model;

    public Players() throws SQLException {
        playersRepository = new PlayersRepository();
        countryRepository = new CountryRepository();
        teamRepository = new TeamRepository();

        if (UserService.currentUser.getRoleId() == 2) {
            editPanel.setVisible(false);
        }

        Object[] columns = {"Id", "First Name", "Surname", "Team", "Country" };
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        table.setModel(model);

        FillTable();
        addBtn.addActionListener(e -> {
            try {
                Add();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        deleteBtn.addActionListener(e -> {
            try {
                Delete();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                FillEditField();
            }
        });
        editBtn.addActionListener(e -> {
            try {
                Edit();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
    }

    private void Add() throws SQLException {
        String name = nameField.getText();
        String sur = surnameField.getText();
        String country = countryField.getText();
        String team = teamField.getText();
        String num = numberField.getText();

        String error = ValidatePlayer(name, sur, country, team, num);
        errorLabel.setText(error);

        if (error.length() > 0) {
            return;
        }

        Player player = new Player(Integer.parseInt(team), Integer.parseInt(country), name, sur, Integer.parseInt(num));

        playersRepository.Save(player);

        FillTable();
    }

    private void Delete() throws SQLException {
        int i = table.getSelectedRow();

        if (i >= 0) {
            Player player = GetPlayerFromTable();

            playersRepository.Delete(player.getId());
        }

        FillTable();
    }

    private String ValidatePlayer(String name, String surname, String country, String team, String num) throws SQLException {
        String error = "";
        if (name.isEmpty() || surname.isEmpty() || country.isEmpty() || team.isEmpty() || num.isEmpty()) {
            error = "All fields are required";

            return error;
        }

        String NSPattern = "[A-Z][A-z]+";

        if (!name.matches(NSPattern)) {
            error = "Use Latin letters for the name";
            return error;
        }

        if (!surname.matches(NSPattern)) {
            error = "Use Latin letters for the Surname";
            return error;
        }

        if (!TryParseInt(country)) {
            error = "CountryId must be int";
            return error;
        }

        if (!TryParseInt(team)) {
            error = "TeamId must be int";
            return error;
        }

        if (!TryParseInt(num)) {
            error = "Number must be int";
            return error;
        }

        List<Team> teams = teamRepository.Get();
        List<Country> countries = countryRepository.Get();

        int cId = Integer.parseInt(country);
        int tId = Integer.parseInt(team);

        if (!teams.stream().anyMatch(x -> x.getId() == tId)) {
            error = "Choose correct team Id";
            return error;
        }

        if (!countries.stream().anyMatch(x -> x.getId() == cId)) {
            error = "Choose correct county Id";
            return error;
        }

        return error;
    }

    private void Edit() throws SQLException {
        Player player = GetPlayerFromTable();

        FillPlayerFromEditFields(player);

        String name = nameField.getText();
        String sur = surnameField.getText();
        String country = countryField.getText();
        String team = teamField.getText();
        String num = numberField.getText();

        String error = ValidatePlayer(name, sur, country, team, num);
        errorLabel.setText(error);

        if (error.length() > 0) {
            return;
        }

        playersRepository.Update(player);

        FillTable();
    }

    private void FillPlayerFromEditFields(Player player) {
        player.setNumber(Integer.parseInt(numberField.getText()));
        player.setTeamId(Integer.parseInt(teamField.getText()));
        player.setCountryId(Integer.parseInt(countryField.getText()));
        player.setName(nameField.getText());
        player.setSurname(surnameField.getText());
    }

    private Player GetPlayerFromTable() {
        int i = table.getSelectedRow();

        int playerId = Integer.parseInt(model.getValueAt(i, 0).toString());

        Player player = players.stream().filter(x -> x.getId() == playerId).findFirst().get();

        return player;
    }

    private void FillEditField() {
        Player player = GetPlayerFromTable();

        FillEditField(player);
    }

    private void FillEditField(Player player) {
        nameField.setText(player.getName());
        surnameField.setText(player.getSurname());
        teamField.setText(Integer.toString(player.getTeamId()));
        countryField.setText(Integer.toString(player.getCountryId()));
        numberField.setText(Integer.toString(player.getNumber()));
    }

    private boolean TryParseInt(String val) {
        try {
            Integer.parseInt(val);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void FillTable() throws SQLException {
        model.setRowCount(0);

        players = playersRepository.Get();
        FillTable(players);
    }

    public void FillTable(List<Player> players) {
        for(Player player : players) {
            Object[] row = new Object[5];

            row[0] = player.getId();
            row[1] = player.getName();
            row[2] = player.getSurname();
            row[3] = player.getTeamName();
            row[4] = player.getCountryName();

            model.addRow(row);
        }
    }


}
