# WxHelper使用说明
> 该工具封装了搭建微信后台时需要用到的一些方法  
#### 检验微信发送的GET请求
在微信公众平台填写好服务器接口后，微信会发送一条GET请求到该接口上，服务器需要检验请求中的信息（请求的信息里包括四个参数：signature、timestamp、nonce、echostr，服务器需要通过规定的算法对前三个参数及使用者自行定义的字符串token进行校验，如果校验成功，则将echostr原样返回，完成接入）。  
你可以直接在Controller中使用**WxHelper.respond()** 方法，以实现响应请求的功能：  
```
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public void doGet(
            HttpServletResponse response,
            @RequestParam(value = "signature", required = true) String signature,
            @RequestParam(value = "timestamp", required = true) String timestamp,
            @RequestParam(value = "nonce", required = true) String nonce,
            @RequestParam(value = "echostr", required = true) String echostr) throws IOException {

        WxHelper.respond(response.getWriter(),TOKEN,
                signature,timestamp,nonce,echostr);
    }
```  
#### 更新、获取Access_Token  
access_token是公众号的全局唯一接口调用凭据，公众号调用各接口时都需使用该凭据。  
access_token的有效期是两个小时，开发者所搭建的微信后台务必每隔两个小时获取并更新一次access_token。  
你可以使用**AccessTokenUtil.update()** 方法更新access_token，access_token以静态变量的形式存储在AccessTokenUtil中，随时可以使用**AccessTokenUtil.getAccessToken()** 得到它。  
为了保证access_token每隔两小时进行一次更新，以spring框架为例，需要开启计划任务功能，并设置定时任务：  
```
@Service
public class ScheduleService {

    //定义appId、secret、grantType

    @Scheduled(fixedRate = 7200000)
    public void updateAccessTokenInTime(){
        AccessToken.update(appId,secret,grantType);
    }
}
```
#### 发送模板消息、接收微信服务器推送的xml数据 
**WxHelper**提供了两个bean用于处理开发者服务器与微信服务器交互的数据。  
如果公众号要向关注者推送模板消息，就需要开发者的服务器向微信服务器（api.weixin.qq.com）发送一条POST请求，这时可以用到WxHelper中的**SendMsg**类：  
```
String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" 
    + AccessTokenUtil.getAccessToken();

//配置sendMsg
SendMsg msg = new SendMsg();
msg.setToUser("oWafF0wSXRWGasOJXUeYxViqXyqo");
msg.setTemplateId("GwPvwbNgKo-HhpGp0U7vWxqaZl3f-zDCg61dI8tDh80");
msg.setTopColor("#FF0000");
Map<String, SendMsg.DataValue> map = new HashMap<>();
map.put("status",new SendMsg.DataValue("normal","#173177"));
msg.setData(map);

//执行post请求
RestTemplate restTemplate = new RestTemplate();
ResponseEntity<String> responseEntity = restTemplate
    .postForEntity(url, msg, String.class);
```
发送完模板信息后（或是一些其他情况），微信服务器会以post方式向开发者的服务器发送xml数据（所访问的接口和之前用于校验微信请求的是同一个，只不过请求方式是POST而不再是GET）  
**WxEvent**则可以用于封装这个xml数据，配合spring框架可以直接把请求的xml数据转换成WxEvent的实例对象：   
```
    @RequestMapping(value = "/check", method = RequestMethod.POST, consumes = "text/xml")
    @ResponseBody
    public void doPost(@RequestBody WxEvent event){
        System.out.println(event.getEvent());
    }
```

