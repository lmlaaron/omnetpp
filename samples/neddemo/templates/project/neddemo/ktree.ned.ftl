<#-- template include for network.ned.ftl -->

<#if generateNodeTypeDecl>
module ${nodeTypeName} {
    parameters:
        @display("i=misc/node_vs");
    gates:
        inout parent;
        inout child[];
}
</#if>

<#if generateChannelTypeDecl && channelTypeName!="">
channel ${channelTypeName} extends ned.DatarateChannel {
    parameters:
        int cost = default(0);
}
</#if>

<#-- TODO: generateCoordinates -->
<#if parametricNED>
   <#if treeK==2>
network ${networkName}
{
    parameters:
        int levels = default(${treeLevels});  // 1 = root only
    submodules:
        node[2^levels-1]: ${nodeTypeName};
    connections:
        for i=1..sizeof(node)-1 {
            node[i].parent <-->${channelSpec} node[floor((i-1)/2)].child++;
        }
}
  <#else>
network ${networkName}
{
    parameters:
        int k = default(${treeK}); // tree branching factor
        int levels = default(${treeLevels});  // 1 = root only
    submodules:
        node[(k^levels-1) / (k-1)]: ${nodeTypeName};
    connections:
        for i=1..sizeof(node)-1 {
            node[i].parent <-->${channelSpec} node[floor((i-1)/k).child++;
        }
}
  </#if>
<#else>
network ${networkName}
{
    submodules:
<#list 0..treeLevels-1 as level>
  <#list 0..Math.pow(treeK, level)-1 as i>
        node_${level}_${i}: ${nodeTypeName};
  </#list>

</#list>
    connections:
<#-- connect each node to its parent -->
<#list 1..treeLevels-1 as level>
  <#list 0..Math.pow(treeK, level)-1 as i>
        node_${level}_${i}.parent <-->${channelSpec} node_${level-1}_${(i/treeK)?floor}.child++;
  </#list>

</#list>
}
</#if>

