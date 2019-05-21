import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaoRubbish implements RubbishDao {

    public DaoRubbish() {
    }

    private static class SingletonHelper {
        private static final DaoRubbish INSTANCE = new DaoRubbish();
    }

    public static DaoRubbish getInstance() {
        return SingletonHelper.INSTANCE;
    }

    @Override
    public Optional<Rubbish> find(String s) throws SQLException {
        String sql = "select rubbish_id, name, description, quantity, location from rubbish where rubbish_id = ?";
        int id_rubbish = 0, quantity = 0;
        String name = "", description = "", location = "";
        Connection conn = DataSourceFactory.getConn();

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, sql);
        ResultSet resultset= statement.executeQuery();

        if(resultset.next()) {
            id_rubbish = resultset.getInt("rubbish_id");
            quantity = resultset.getInt("quantity");
            name = resultset.getString("name");
            description = resultset.getString("description");
            location = resultset.getString("location");
        }

        return Optional.of(new Rubbish(id_rubbish, name, description, quantity, location));
    }

    @Override
    public List<Rubbish> findAll() throws SQLException {
        List<Rubbish> listrubbish = new ArrayList<>();
        String sql = "select rubbish_id, name, description, quantity, location from rubbish";

        Connection conn = DataSourceFactory.getConn();
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet resultset = statement.executeQuery();

        while(resultset.next()) {
            int id_rubbish = resultset.getInt("rubbish_id");
            int quantity = resultset.getInt("quantity");
            String name = resultset.getString("name");
            String description = resultset.getString("description");
            String location = resultset.getString("location");

            Rubbish rubbish = new Rubbish(id_rubbish, name, description, quantity, location);
            listrubbish.add(rubbish);
        }

        return listrubbish;
    }

    @Override
    public boolean save(Rubbish o) throws SQLException {
        String sql = "insert into rubbish (name, description, quantity, location) values (?, ?, ?, ?)";
        boolean booleanInsert = false;

        Connection conn = DataSourceFactory.getConn();
        PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, o.getName());
            statement.setString(2, o.getDescription());
            statement.setInt(3, o.getQuantity());
            statement.setString(4, o.getLocation());
            booleanInsert = statement.executeUpdate() > 0;

        return booleanInsert;
    }

    @Override
    public boolean update(Rubbish o) throws SQLException {
        String sql = "update rubbish set name = ?, description = ?, quantity = ?, location = ? where rubbish_id = ?";
        boolean booleanUpdate = false;

        Connection conn = DataSourceFactory.getConn();
        PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, o.getName());
            statement.setString(2, o.getDescription());
            statement.setInt(3, o.getQuantity());
            statement.setString(4, o.getLocation());
            statement.setInt(5, o.getId());
            booleanUpdate = statement.executeUpdate() > 0;

        return booleanUpdate;
    }

    @Override
    public boolean delete(Rubbish o) throws SQLException {
        String sql = "delete from rubbish where rubbish_id = ?";
        boolean booleanDelete = false;

        Connection conn = DataSourceFactory.getConn();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, o.getId());
        booleanDelete = statement.executeUpdate() > 0;

        return booleanDelete;
    }
}
