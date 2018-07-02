package com.example.tang.jwt_oauth_tin.services;

import com.example.tang.jwt_oauth_tin.models.TokenValidList;
import com.example.tang.jwt_oauth_tin.repositories.TokenBlackListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TokenBlackListService {

    private TokenBlackListRepository tokenBlackListRepository;

    @Autowired
    public TokenBlackListService(TokenBlackListRepository tokenBlackListRepository) {
        this.tokenBlackListRepository = tokenBlackListRepository;
    }

    public boolean isBlackListed(String jti) throws TokenNotFoundException {
        Optional<TokenValidList> token = tokenBlackListRepository.findByJti(jti);
        if(token.isPresent())
            return token.get().getIsBlackListed();
        else
            throw new TokenNotFoundException(jti);
    }

    @Async
    public void addToEnabledList(Long userId, String jti, Long expired) {
        // clean all black listed token for user
        List<TokenValidList> list = tokenBlackListRepository
                                    .queryAllByUserIdAndIsBlackListedTrue(userId);

        if (list != null && list.size() > 0) {
            list.forEach(
                    token -> {
                        token.setIsBlackListed(true);
                        tokenBlackListRepository.save(token);
                    }
            );
        }

        // add new token white listed
        TokenValidList tokenBlackList = new TokenValidList(jti, userId ,expired);
        tokenBlackList.setIsBlackListed(false);
        tokenBlackListRepository.save(tokenBlackList);
        tokenBlackListRepository.deleteAllByUserIdAndExpiresBefore(userId,new Date().getTime());
    }

    @Async
    public void addToBlackList(String jti) throws TokenNotFoundException {
        Optional<TokenValidList> tokenBlackList = tokenBlackListRepository.findByJti(jti);

        if (tokenBlackList.isPresent()) {
            tokenBlackList.get().setIsBlackListed(true);
            tokenBlackListRepository.save(tokenBlackList.get());
        } else {
            throw new TokenNotFoundException(jti);
        }
    }


}
