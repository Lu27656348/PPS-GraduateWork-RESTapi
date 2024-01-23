package com.example.demo.repository;

import com.example.demo.entity.ExternalPersonnel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExternalPersonnelRepository extends CrudRepository<ExternalPersonnel,String> {

    @Query(
            value = "SELECT Ext.*\n" +
                    "FROM externalPersonnel AS Ext, Enterprises AS E\n" +
                    "WHERE E.enterpriseId = Ext.externalpersonnelenterpriseId\n" +
                    "AND Ext.externalpersonnelenterpriseId = :enterpriseId",
            nativeQuery = true
    )
    public Iterable<ExternalPersonnel> getExternalPersonnelByEnterpriseId (@Param("enterpriseId") Integer enterpriseid);
}
