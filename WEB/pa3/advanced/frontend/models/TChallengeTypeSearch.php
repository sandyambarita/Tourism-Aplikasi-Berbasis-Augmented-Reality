<?php

namespace frontend\models;

use Yii;
use yii\base\Model;
use yii\data\ActiveDataProvider;
use frontend\models\TChallengeType;

/**
 * TChallengeTypeSearch represents the model behind the search form about `frontend\models\TChallengeType`.
 */
class TChallengeTypeSearch extends TChallengeType
{
    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['challenge_type_id'], 'integer'],
            [['type_name', 'description'], 'safe'],
        ];
    }

    /**
     * @inheritdoc
     */
    public function scenarios()
    {
        // bypass scenarios() implementation in the parent class
        return Model::scenarios();
    }

    /**
     * Creates data provider instance with search query applied
     *
     * @param array $params
     *
     * @return ActiveDataProvider
     */
    public function search($params)
    {
        $query = TChallengeType::find();

        // add conditions that should always apply here

        $dataProvider = new ActiveDataProvider([
            'query' => $query,
        ]);

        $this->load($params);

        if (!$this->validate()) {
            // uncomment the following line if you do not want to return any records when validation fails
            // $query->where('0=1');
            return $dataProvider;
        }

        // grid filtering conditions
        $query->andFilterWhere([
            'challenge_type_id' => $this->challenge_type_id,
        ]);

        $query->andFilterWhere(['like', 'type_name', $this->type_name])
            ->andFilterWhere(['like', 'description', $this->description]);

        return $dataProvider;
    }
}
