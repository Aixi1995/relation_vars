package com.example.repository;

import entity.ProdDefine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/8/21 15:34
 * @desc
 */
@Service
public interface ProdDefineRepository extends JpaRepository<ProdDefine, Integer> {
}
