package com.lam.quan.intuitrepo

import com.lam.quan.intuitrepo.model.Issue
import com.lam.quan.intuitrepo.model.Repo
import com.lam.quan.intuitrepo.model.User
import org.junit.Assert
import org.junit.Test

class DataModelTest {

    @Test
    fun dataModelTest() {
        //This is a sample test. There're not much to test with these classes.
        val repo = Repo()
        Assert.assertNotNull(repo)
        val issue = Issue()
        Assert.assertNotNull(issue)
        val user = User()
        Assert.assertNotNull(user)
    }
}