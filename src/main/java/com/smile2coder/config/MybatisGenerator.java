package com.smile2coder.config;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zxt
 * @date 12/21/20
 * @desc
 */
public class MybatisGenerator {

    public void generator() throws Exception {

        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        //指定逆向工程配置文件
        String path = this.getClass().getClassLoader().getResource("mybatis-generator-config.xml").getPath();
        File configFile = new File(path);
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,
                callback, warnings);
        myBatisGenerator.generate(null);

    }

    public static void main(String[] args) {
        try {
            MybatisGenerator generator = new MybatisGenerator();
            generator.generator();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
