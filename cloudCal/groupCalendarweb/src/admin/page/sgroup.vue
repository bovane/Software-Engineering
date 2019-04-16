<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-setting"></i> 群组管理</el-breadcrumb-item>
                <el-breadcrumb-item>群组搜索</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div>
            <el-form  :inline="true" :model="form" :rules="rules" ref="form" style="margin-top: 10%;margin-left: 16%">
                <el-form-item label="搜索 :" prop="groupname">
                    <el-input clearable style="width: 218%" placeholder="请输入群组名" v-model="form.groupname" @keyup.enter.native="search(form)" >
                    </el-input>
                </el-form-item>
                <el-form-item style="margin-left: 22%">
                    <el-button type="primary" @click="search(form)">搜索</el-button>
                </el-form-item>
            </el-form>
        </div>
        <div>
            <el-table :data="data" border style="width: 80%;margin-left: 12%" ref="multipleTable" v-if="come">
                <el-table-column label="群组名" prop="groupname" width="150px"></el-table-column>
                <el-table-column label="群主" prop="owner" width="100px"></el-table-column>
                <el-table-column label="描述" prop="groupdesc" show-overflow-tooltip></el-table-column>
                <el-table-column label="操作" width="100px">
                    <template slot-scope="scope" width="100px">
                        <el-button type="text" @click="add(scope.row)" >申请加群</el-button>
                    </template>
                </el-table-column>
            </el-table>
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
                data:[],
                come:false,
                form:{
                    groupname:'',
                },
                rules:{

                },
                group:[{
                    groupname: 'A组',
                    notes: '这是一个测试',
                    owner:'admin'
                }, {
                    groupname: '浙江工商组',
                    notes: '浙江工商大学',
                    owner:'admin'
                }, {
                    groupname: '努力学习组',
                    notes: '来自学霸的集结',
                    owner:'admin'
                }, {
                    groupname: '电竞娱乐组',
                    notes: '宅男们的日常生活小组',
                    owner:'admin'
                }],
            }
        },
        created(){
            if(localStorage.getItem('ms_username')==""){
                this.$router.replace('/login')
            }else{
                this.search(this.form)
            }
        },
        methods:{
            search(form){
                this.data=[]
                this.come=true
                if(form.groupname==""){
                    this.$http.post("/demo/gtotal/listgroup").then(
                        success=>{
                            this.data=success.data
                        }
                    )
                }else{
                    let params={
                        "groupname":form.groupname,
                    }
                    console.log(params)
                    this.$http.post("/demo/gtotal/findgroup",params,{emulateJSON:true}).then(
                        success=>{
                            this.data=success.data
                        }
                    )
                }
            },
            add(row){
                let params={
                    "sender":localStorage.getItem('ms_username'),
                    "receiver":row.owner,
                    "type":3,
                    "eventid":0,
                    "groupname":row.groupname,
                }
                this.$http.post("/demo/message/addmeg",params,{emulateJSON:true}).then(
                    success=>{
                        this.$message({
                            type:'success',
                            message:'申请成功，请等候管理员审核！'
                        })
                    }
                )
            },
        }
    }
</script>

<style scoped>

</style>
