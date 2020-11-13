package com.streamingserviceserver.model.streamingserviceconnection;

import com.streamingserviceserver.database.DatabaseHandler;
import com.streamingserviceserver.database.ISqlHandler;
import com.streamingserviceserver.model.BaseMedia;
import com.streamingserviceserver.model.Dao;
import com.streamingserviceserver.model.movie.Movie;
import com.streamingserviceserver.model.show.Show;
import com.streamingserviceserver.model.streamingservice.StreamingService;
import com.streamingserviceserver.model.streamingservice.StreamingServiceDAO;

import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class StreamingServiceConnectionDao {

    private final ISqlHandler _sqlHandler;
    private final StreamingServiceDAO streamingServiceDAO;

    public StreamingServiceConnectionDao(ISqlHandler sqlHandler) {
        this._sqlHandler = sqlHandler;
        streamingServiceDAO = new StreamingServiceDAO(sqlHandler);
    }

    public List<StreamingServiceConnection> getServicesForMovie(Movie m) {
        String sql = "SELECT * FROM " + DatabaseHandler.TABLE_SERVICE_MOVIE + " WHERE " + DatabaseHandler.COLUMN_MOVIE_ID + " = '" + m.getId() + "'";
        return getListFromQuery(sql, true);
    }

    public List<StreamingServiceConnection> getServicesForShow(Show s) {
        String sql = "SELECT * FROM " + DatabaseHandler.TABLE_SERVICE_SHOW + " WHERE " + DatabaseHandler.COLUMN_SHOW_ID + " = '" + s.getId() + "'";
        return getListFromQuery(sql, false);
    }

    public boolean addConnectionForMovie(String serviceId, String movieId, Double rentalPrice) {
        String template = "INSERT INTO " + DatabaseHandler.TABLE_SERVICE_MOVIE + " VALUES ('%s', '%s', %.2f)";
        return _sqlHandler.executeSql(String.format(template, serviceId, movieId, rentalPrice));
    }

    public boolean addConnectionForShow(String serviceId, String showId) {
        String template = "INSERT INTO " + DatabaseHandler.TABLE_SERVICE_SHOW + " VALUES ('%s', '%s')";
        return _sqlHandler.executeSql(String.format(template, serviceId, showId));
    }

    private List<StreamingServiceConnection> getListFromQuery(String query, boolean checkRental) {
        Map<String, StreamingServiceConnection> connections = new HashMap<>();
        try {
            ResultSet rs = _sqlHandler.executeQuery(query);
            while(rs != null && rs.next()) {
                StreamingServiceConnection connection = getFromResultSet(rs, checkRental);
                connections.put(connection.getId(), connection);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return new ArrayList<>(connections.values());
    }

    private StreamingServiceConnection getFromResultSet(ResultSet rs, boolean checkRental) throws SQLException {
        String id = rs.getString(1);
        Double rentalPrice = 0d;
        if (checkRental) {
            rentalPrice = rs.getDouble(3);
        }

        String name = "";
        Optional<StreamingService> service = streamingServiceDAO.get(id);
        if (service.isPresent()) {
            name = service.get().getName();
        }

        return new StreamingServiceConnection(id, name, rentalPrice);
    }
}
