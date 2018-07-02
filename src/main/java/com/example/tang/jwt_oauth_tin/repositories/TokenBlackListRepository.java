package com.example.tang.jwt_oauth_tin.repositories;

import com.example.tang.jwt_oauth_tin.models.TokenValidList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TokenBlackListRepository extends JpaRepository<TokenValidList, Long> {

    Optional<TokenValidList> findByJti(String jti);
    List<TokenValidList> queryAllByUserIdAndIsBlackListedTrue(Long userId);
    TokenValidList save(TokenValidList tokenBlackList);
    List<TokenValidList> deleteAllByUserIdAndExpiresBefore(Long userId, Long data);
}
