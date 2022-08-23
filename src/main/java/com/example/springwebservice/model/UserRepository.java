package com.example.springwebservice.model;

import com.example.springwebservice.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.lang.model.element.Name;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findById(int id);
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM member WHERE id =?1", nativeQuery = true)
    void deleteById(int id);

    @Query(value = "SELECT * FROM member where age>=?1", nativeQuery = true)
    List<User> findByAgeGreaterThanEqual(int age);

    @Query(value = "SELECT * FROM member ORDER BY age DESC", nativeQuery = true)
    List<User> findAllByUserByAgeDesc();

    @Query(value = "SELECT * FROM member where age=?1 and name=?2", nativeQuery = true)
    List<User> findByNameAndAge(int age,String name);

    @Query(value = "INSERT INTO member(id,age,name)VALUES(?1,?2,?3)", nativeQuery = true)
    @Modifying
    @Transactional
    void createUserBySql(int id,int age,String name);

    @Query(value = "UPDATE member SET age=?1, name=?2 WHERE id=?3 ", nativeQuery = true)
    @Modifying
    @Transactional
    void updateUserBySql(int age,String name,int id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM member WHERE name =?1 AND age=?2 ", nativeQuery = true)
    void deleteByNameAndAge(String name,int age);


}

