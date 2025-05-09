package com.comitfy.kidefy.userModule.repository;

import com.comitfy.kidefy.userModule.entity.Role;
import com.comitfy.kidefy.util.common.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends BaseRepository<Role> {

    Optional<Role> findByName(String name);

    //List<Role> findAllByUsers_Email(String email);

    List<Role> findAllByNameContains(String name, Pageable pageable);

    @Query("SELECT role FROM Role role" +
            " inner join role.users user WHERE user.uuid=?1")
    Page<Role> getUserRolesByUserId(Pageable pageable, UUID userUUID);

    Optional<Role> findByUuid(UUID roleId);
    //Role findByRoleName(String roleName);




}
