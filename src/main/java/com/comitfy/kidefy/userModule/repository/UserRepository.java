package com.comitfy.kidefy.userModule.repository;


import com.comitfy.kidefy.userModule.entity.User;
import com.comitfy.kidefy.util.common.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends BaseRepository<User> {

    Optional<User> findByEmail(String email);





    @Query("SELECT user FROM User user" +
            " inner join user.roles role WHERE role.uuid=?1")
    Page<User> getUserByRole(Pageable pageable, UUID roleUUID);

    Optional<User> findByUuid(UUID userId);
}
