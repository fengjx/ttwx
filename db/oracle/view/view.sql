create or replace view keyword_action_view as
select a . id AS id,
       a . req_type AS req_type,
       a . action_type AS action_type,
       a . key_word AS key_word,
       a . material_id AS material_id,
       a . app_id AS app_id,
       to_char(a . in_time, 'yyyy-mm-dd hh24:mi:ss') AS in_time,
       a . user_id AS user_id,
       b . beanName AS beanName,
       b . methodName AS methodName,
       b . name AS app_name,
       c . xml_data AS xml_data,
       c . msg_type AS msg_type,
       d . dict_name AS dict_name
  from (((wechat_resp_msg_action a left join wechat_ext_app b
        on((a . app_id = b . id))) left join wechat_material c
        on((a . material_id = c . id))) left join wechat_data_dict d
        on((c . msg_type = d . dict_value)))
 where (d . group_code = 'resp_type');
