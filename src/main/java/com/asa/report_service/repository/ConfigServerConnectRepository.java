package com.asa.report_service.repository;

import com.asa.report_service.entity.ConfigServerConnect;
import com.asa.report_service.entity.enums.DatabaseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConfigServerConnectRepository extends JpaRepository<ConfigServerConnect, Long> {

    List<ConfigServerConnect> findByServerName(String serverName);

    List<ConfigServerConnect> findByUserId(Integer userId);

    List<ConfigServerConnect> findByIsDeletedFalse();

    List<ConfigServerConnect> findByBdType(DatabaseType bdType);

    List<ConfigServerConnect> findByBdTypeAndUserId(DatabaseType bdType, Integer userId);

    @Query("SELECT c FROM ConfigServerConnect c WHERE c.serverName LIKE %:keyword% OR c.serverDescription LIKE %:keyword%")
    List<ConfigServerConnect> searchByKeyword(@Param("keyword") String keyword);

    Optional<ConfigServerConnect> findByServerNameOrIp(String serverNameOrIp);

    List<ConfigServerConnect> findByServerPort(Integer port);

    List<ConfigServerConnect> findByDatabaseName(String databaseName);
}