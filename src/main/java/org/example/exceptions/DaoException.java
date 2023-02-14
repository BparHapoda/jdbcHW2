package org.example.exceptions;

import java.sql.SQLException;

public class DaoException extends SQLException {

    public DaoException() {
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }
}
