const base = {
    get() {
        return {
            url : "http://localhost:8080/springboote4e34i32/",
            name: "springboote4e34i32",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/springboote4e34i32/front/dist/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "明星粉丝应援系统"
        } 
    }
}
export default base
