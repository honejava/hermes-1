package com.ctrip.hermes.metaservice.service.storage.handler;

import java.util.List;

import com.ctrip.hermes.metaservice.service.storage.exception.StorageHandleErrorException;
import com.ctrip.hermes.metaservice.service.storage.model.TableModel;
import com.ctrip.hermes.metaservice.service.storage.pojo.StoragePartition;
import com.ctrip.hermes.metaservice.service.storage.pojo.StorageTable;

public interface StorageHandler {

	public boolean dropTables(Long topicId, Integer partitionId, List<TableModel> model,
									  String datasource) throws StorageHandleErrorException;

	public void createTable(Long topicId, Integer partitionId, List<TableModel> model,
									String datasource) throws StorageHandleErrorException;


	public void addPartition(Long topicId, Integer partitionId, TableModel model, int range,
									 String datasource) throws StorageHandleErrorException;

	public void addPartition(String table, int range, String datasource) throws StorageHandleErrorException;

	public void deletePartition(Long topicId, Integer partitionId, TableModel model,
										 String datasource) throws StorageHandleErrorException;

	public void deletePartition(String table, String datasource) throws StorageHandleErrorException;

	public List<StorageTable> queryTable(Long topicId, Integer partitionId, String datasource) throws StorageHandleErrorException;

	public List<StorageTable> queryAllTablesInDatasourceWithoutPartition(String datasource) throws StorageHandleErrorException;

	public Integer queryAllSizeInDatasource(String datasource) throws StorageHandleErrorException;

	public Integer queryTableSize(String table, String datasource) throws StorageHandleErrorException;

	public List<StoragePartition> queryTablePartitions(String table, String datasource) throws StorageHandleErrorException;
}
