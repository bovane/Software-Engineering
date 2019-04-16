<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-setting"></i> 群组管理</el-breadcrumb-item>
                <el-breadcrumb-item>群组创建</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div>
            <el-form :model="form" :rules="rules" ref="form" style="margin-top: 8%;margin-left: 5%;" label-width="150px">
                <el-form-item label="群组名" prop="groupname" style="width: 50%;">
                    <el-input  placeholder="请输入群组名" v-model="form.groupname" clearable></el-input>
                </el-form-item>
                <el-form-item label="描述" prop="notes" style="width: 50%;">
                    <el-input type="textarea" placeholder="请输入对该群的描述" v-model="form.notes" clearable></el-input>
                </el-form-item>
                <el-form-item style="margin-left: 8%">
                    <el-button type="primary" @click="back">返回</el-button>
                    <el-button type="primary" @click="submit(form)">创建</el-button>
                </el-form-item>
            </el-form>
        </div>

    </div>
</template>

<script>
    export default {
        data: function(){
            var checkpasswd = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请再次确认新密码'));
                } else if (value !== this.form.password1) {
                    callback(new Error('两次输入的新密码不一致!'));
                } else {
                    callback();
                }
            };
            var checkpasswd1 = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请再次确认新密码'));
                } else if (value !== this.form1.password1) {
                    callback(new Error('两次输入的新密码不一致!'));
                } else {
                    callback();
                }
            };
            var checkuser = (rule, value, callback) => {
                this.$http.post("/demo/admin/listuser").then(
                    success=>{
                        for(let i=0;i<success.data.length;i++){
                            if(value == success.data[i].username){
                                callback(new Error('该用户名已存在'));
                            }
                        }
                    }
                )
            }
            return {
                form:{
                    groupname:'',
                    notes:'',
                },
                rules:{

                },
            }
        },
        created(){
            if(localStorage.getItem('ms_username')==""){
                this.$router.replace('/login')
            }
        },
        methods:{
            date(value) {           //将createtime变为普通日期
                let date = new Date(value);
                let   Y = date.getFullYear(),
                    m = date.getMonth() + 1,
                    d = date.getDate(),
                    H = date.getHours(),
                    i = date.getMinutes(),
                    s = date.getSeconds();
                if (m < 10) {
                    m = '0' + m;
                }
                if (d < 10) {
                    d = '0' + d;
                }
                if (H < 10) {
                    H = '0' + H;
                }
                if (i < 10) {
                    i = '0' + i;
                }
                if (s < 10) {
                    s = '0' + s;
                }
                let t = Y+'-'+m+'-'+d+' '+H+':'+i+':'+s;
                return t;
            },
            submit(form){
                let params={
                    "groupname":form.groupname,
                    "groupdesc":form.notes,
                    "owner":localStorage.getItem('ms_username'),
                }
                this.$http.post("/demo/gtotal/addgrp",params,{emulateJSON:true}).then(
                    success=>{
                        this.$message({
                            type:'success',
                            message:'创建成功!'
                        })
                    }
                )
            },
            back(){
                this.$router.replace('/sgroup')
            },
        }
    }
</script>

<style scoped>

</style>
