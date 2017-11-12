/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.jackrabbit.oak.plugins.document.rdb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.Locale;

public class RDBStatementWrapper implements Statement {

    private final Statement statement;
    private final boolean failAlterTableAddColumnStatements;

    public RDBStatementWrapper(Statement statement, boolean failAlterTableAddColumnStatements) {
        this.statement = statement;
        this.failAlterTableAddColumnStatements = failAlterTableAddColumnStatements;
    }

    public void addBatch(String sql) throws SQLException {
        statement.addBatch(sql);
    }

    public void cancel() throws SQLException {
        statement.cancel();
    }

    public void clearBatch() throws SQLException {
        statement.clearBatch();
    }

    public void clearWarnings() throws SQLException {
        statement.clearWarnings();
    }

    public void close() throws SQLException {
        statement.close();
    }

    public void closeOnCompletion() throws SQLException {
        statement.closeOnCompletion();
    }

    public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
        return statement.execute(sql, autoGeneratedKeys);
    }

    public boolean execute(String sql, int[] columnIndexes) throws SQLException {
        return statement.execute(sql, columnIndexes);
    }

    public boolean execute(String sql, String[] columnNames) throws SQLException {
        return statement.execute(sql, columnNames);
    }

    public boolean execute(String sql) throws SQLException {
        if (this.failAlterTableAddColumnStatements) {
            String l = sql.toLowerCase(Locale.ENGLISH).trim();
            if (l.startsWith("alter table ") && l.contains(" add ")) {
                if (!l.contains(" constraint ")) {
                    throw new SQLException("table alter statement rejected: " + sql);
                }
            }
        }
        return statement.execute(sql);
    }

    public int[] executeBatch() throws SQLException {
        return statement.executeBatch();
    }

    public ResultSet executeQuery(String sql) throws SQLException {
        return statement.executeQuery(sql);
    }

    public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
        return statement.executeUpdate(sql, autoGeneratedKeys);
    }

    public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
        return statement.executeUpdate(sql, columnIndexes);
    }

    public int executeUpdate(String sql, String[] columnNames) throws SQLException {
        return statement.executeUpdate(sql, columnNames);
    }

    public int executeUpdate(String sql) throws SQLException {
        return statement.executeUpdate(sql);
    }

    public Connection getConnection() throws SQLException {
        return statement.getConnection();
    }

    public int getFetchDirection() throws SQLException {
        return statement.getFetchDirection();
    }

    public int getFetchSize() throws SQLException {
        return statement.getFetchSize();
    }

    public ResultSet getGeneratedKeys() throws SQLException {
        return statement.getGeneratedKeys();
    }

    public int getMaxFieldSize() throws SQLException {
        return statement.getMaxFieldSize();
    }

    public int getMaxRows() throws SQLException {
        return statement.getMaxRows();
    }

    public boolean getMoreResults() throws SQLException {
        return statement.getMoreResults();
    }

    public boolean getMoreResults(int current) throws SQLException {
        return statement.getMoreResults(current);
    }

    public int getQueryTimeout() throws SQLException {
        return statement.getQueryTimeout();
    }

    public ResultSet getResultSet() throws SQLException {
        return statement.getResultSet();
    }

    public int getResultSetConcurrency() throws SQLException {
        return statement.getResultSetConcurrency();
    }

    public int getResultSetHoldability() throws SQLException {
        return statement.getResultSetHoldability();
    }

    public int getResultSetType() throws SQLException {
        return statement.getResultSetType();
    }

    public int getUpdateCount() throws SQLException {
        return statement.getUpdateCount();
    }

    public SQLWarning getWarnings() throws SQLException {
        return statement.getWarnings();
    }

    public boolean isCloseOnCompletion() throws SQLException {
        return statement.isCloseOnCompletion();
    }

    public boolean isClosed() throws SQLException {
        return statement.isClosed();
    }

    public boolean isPoolable() throws SQLException {
        return statement.isPoolable();
    }

    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return statement.isWrapperFor(iface);
    }

    public void setCursorName(String name) throws SQLException {
        statement.setCursorName(name);
    }

    public void setEscapeProcessing(boolean enable) throws SQLException {
        statement.setEscapeProcessing(enable);
    }

    public void setFetchDirection(int direction) throws SQLException {
        statement.setFetchDirection(direction);
    }

    public void setFetchSize(int rows) throws SQLException {
        statement.setFetchSize(rows);
    }

    public void setMaxFieldSize(int max) throws SQLException {
        statement.setMaxFieldSize(max);
    }

    public void setMaxRows(int max) throws SQLException {
        statement.setMaxRows(max);
    }

    public void setPoolable(boolean poolable) throws SQLException {
        statement.setPoolable(poolable);
    }

    public void setQueryTimeout(int seconds) throws SQLException {
        statement.setQueryTimeout(seconds);
    }

    public <T> T unwrap(Class<T> iface) throws SQLException {
        return statement.unwrap(iface);
    }
}
